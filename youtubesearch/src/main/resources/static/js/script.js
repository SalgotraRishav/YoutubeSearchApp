// Get references to DOM elements
const searchQueryInput = document.getElementById('searchQuery');
const searchButton = document.getElementById('searchButton');
const resultsContainer = document.getElementById('resultsContainer');
const loadingIndicator = document.getElementById('loadingIndicator');
const errorMessageDiv = document.getElementById('errorMessage');
const errorTextSpan = document.getElementById('errorText');

// Function to display error messages
function displayError(message) {
    errorMessageDiv.classList.remove('hidden');
    errorTextSpan.textContent = message;
}

// Function to hide error messages
function hideError() {
    errorMessageDiv.classList.add('hidden');
    errorTextSpan.textContent = '';
}

// Event listener for the search button
searchButton.addEventListener('click', async () => {
    const query = searchQueryInput.value.trim(); // Get and trim the search query

    if (!query) {
        displayError("Please enter a search query.");
        resultsContainer.innerHTML = ''; // Clear previous results
        return;
    }

    hideError(); // Hide any previous errors
    resultsContainer.innerHTML = ''; // Clear previous results
    loadingIndicator.classList.remove('hidden'); // Show loading indicator

    try {
        // Construct the backend API URL
        // This URL is now same-origin as the HTML file itself, so CORS won't be an issue.
		const apiUrl = `http://Youtube-search-env.eba-2ukqcted.us-east-1.elasticbeanstalk.com/api/youtube/search?q=${encodeURIComponent(query)}`;

        // Make the fetch request to your Spring Boot backend
        const response = await fetch(apiUrl);

        if (!response.ok) {
            const errorData = await response.json().catch(() => ({ message: 'Unknown error occurred.' }));
            throw new Error(`Backend Error: ${response.status} ${response.statusText} - ${errorData.message || ''}`);
        }

        const videos = await response.json(); // Parse the JSON response

        if (videos.length === 0) {
            resultsContainer.innerHTML = '<p class="text-center text-gray-500 col-span-full">No videos found for this query.</p>';
            return;
        }

        // Iterate over the video data and create HTML elements
        videos.forEach(video => {
            const videoCard = document.createElement('div');
            videoCard.className = 'bg-gray-50 p-4 rounded-lg shadow-sm flex flex-col space-y-3 transform transition-transform hover:scale-105 duration-200';

            // Thumbnail Image
            if (video.imageUrl) {
                const img = document.createElement('img');
                img.src = video.imageUrl;
                img.alt = video.title || 'Video thumbnail';
                img.className = 'w-full h-40 object-cover rounded-md shadow-sm';
                // Add onerror to handle broken image URLs gracefully
                img.onerror = function() {
                    this.onerror=null; // Prevent infinite loop if fallback also fails
                    this.src='https://placehold.co/480x360/e5e7eb/6b7280?text=No+Image';
                    this.alt='Image not available';
                };
                videoCard.appendChild(img);
            } else {
                // Placeholder if no image URL is available
                const noImgDiv = document.createElement('div');
                noImgDiv.className = 'w-full h-40 bg-gray-200 rounded-md flex items-center justify-center text-gray-500 text-sm';
                noImgDiv.textContent = 'No image available';
                videoCard.appendChild(noImgDiv);
            }

            // Title
            const title = document.createElement('h3');
            title.className = 'text-lg font-semibold text-gray-800 line-clamp-2';
            title.textContent = video.title || 'Untitled Video';
            videoCard.appendChild(title);

            // Description
            const description = document.createElement('p');
            description.className = 'text-sm text-gray-600 line-clamp-3';
            description.textContent = video.description || 'No description available.';
            videoCard.appendChild(description);

            // Publish Time
            const publishTime = document.createElement('p');
            publishTime.className = 'text-xs text-gray-500';
            const date = video.publishTime ? new Date(video.publishTime) : null;
            publishTime.textContent = date ? `Published: ${date.toLocaleDateString()}` : 'Published: N/A';
            videoCard.appendChild(publishTime);

            resultsContainer.appendChild(videoCard);
        });

    } catch (error) {
        console.error('Error fetching videos:', error);
        displayError(`Failed to fetch videos: ${error.message || 'Please check the console for more details.'}`);
    } finally {
        loadingIndicator.classList.add('hidden'); // Hide loading indicator
    }
});

// Optional: Trigger search on Enter key press in the input field
searchQueryInput.addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        searchButton.click();
    }
});

// Optional: Auto-focus on the search input when the page loads
document.addEventListener('DOMContentLoaded', () => {
    searchQueryInput.focus();
});